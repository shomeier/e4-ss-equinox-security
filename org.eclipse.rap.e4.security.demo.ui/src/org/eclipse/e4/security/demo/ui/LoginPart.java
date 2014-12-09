package org.eclipse.e4.security.demo.ui;

import javax.annotation.PostConstruct;
import javax.security.auth.callback.NameCallback;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.security.demo.handler.LoginHandler;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LoginPart
{

	private static final String JAAS_CONFIG_FILE = "data/jaas_config.txt";

	private static Text txt_user;

	private static Text txt_password;

	// private static Composite topComposite;

	private static NameCallback nameCallback;

	// @Inject
	// @Active
	// @Named("NameCallback")
	NameCallback callback;

	// @PostConstruct
	// public void createLoginDialog(Composite parent)
	// {
	// System.out.println("In PC!!!");
	// }

	// @Inject
	@PostConstruct
	public void createLoginDialog(Composite parent, final ECommandService commandService, final EHandlerService handlerService,
		IEclipseContext context)
	// LoginHandler nCB)
	{
		if (callback == null)
			System.out.println("Injected Callback is null ...");
		else
			System.out.println("Injected Callback is NOOOT null ...");
		// IEclipseContext context =
		// EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(this.getClass()).getBundleContext());
		System.out.println("1");
		if (context.containsKey(EPartService.class))
			System.out.println("XContains Part Service");
		if (context.containsKey(MApplication.class))
			System.out.println("XContains MApplication Service");
		if (context.containsKey(EModelService.class))
			System.out.println("XContains EModelService Service");
		if (context.containsKey(NameCallback.class))
			System.out.println("XContains NameCallback -----");
		else
			System.out.println("XContains NOT NameCallback -----");

		NameCallback nCallback = context.get(NameCallback.class);
		if (nCallback == null)
			System.out.println("YYY nCallback us NULL");
		else
			System.out.println("YYY nCallback is NOT nULLLLLL");

		if (context.containsKey(LoginHandler.class))
			System.out.println("XContains LoginHandler -----");
		else
			System.out.println("XContains NOT LoginHandler -----");

		LoginHandler loginHandler = context.get(LoginHandler.class);
		if (loginHandler == null)
			System.out.println("YYY nLoginHandler us NULL");
		else
			System.out.println("YYY nLoginHandler is NOT nULLLLLL");

		// if (nCB == null)
		// System.out.println("YYY nCB us NULL");
		// else
		// System.out.println("YYY nCB is NOT nULLLLLL");

		// set grid layout to parent
		parent.setLayout(new GridLayout());

		Composite topComposite = new Composite(parent, SWT.NONE);

		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;
		layout.makeColumnsEqualWidth = true;// make each column have same width
		layout.numColumns = 2; // number of columns
		layout.verticalSpacing = 10;
		topComposite.setLayout(layout);

		Label lbl_user = new Label(topComposite, SWT.NONE);
		// lbl_user.setText("User:");
		lbl_user.setText(nameCallback.getPrompt());
		txt_user = new Text(topComposite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		GridData gd_user = new GridData(SWT.FILL, SWT.CENTER, true, true);
		// txt_user = new Text(topComposite, SWT.BORDER);
		txt_user.setLayoutData(gd_user);
		txt_user.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent event)
			{
				System.out.println("Triggered NameCB modify listener...");
				nameCallback.setName(txt_user.getText());
			}
		});

		Label lbl_password = new Label(topComposite, SWT.NONE);
		lbl_password.setText("Password:");
		GridData gd_pw = new GridData(SWT.FILL, SWT.CENTER, true, true);
		txt_password = new Text(topComposite, SWT.BORDER | SWT.PASSWORD);
		txt_password.setLayoutData(gd_pw);

		GridData gd_login = new GridData(SWT.FILL, SWT.CENTER, true, true);
		gd_login.horizontalSpan = 2;
		Button btn_login = new Button(topComposite, SWT.NONE);
		btn_login.setText("Login");
		btn_login.setLayoutData(gd_login);
		btn_login.addSelectionListener(new SelectionAdapter()
		{
			@Override
			@SuppressWarnings("restriction")
			public void widgetSelected(SelectionEvent e)
			{
				// txt_user.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

				// programmatically call the LoginCommand via Eclipse 4 Services
				Command command = commandService.getCommand("org.eclipse.e4.security.demo.ui.command.LoginCommand");

				// check if the command is defined
				System.out.println(command.isDefined());

				// activate Handler, assumption: the AboutHandler() class exists already
				handlerService.activateHandler("org.eclipse.e4.security.demo.ui.command.LoginHandler", new LoginHandler());

				ParameterizedCommand cmd =
					commandService.createCommand("org.eclipse.e4.security.demo.ui.command.LoginCommand", null);

				// check if the command can get executed
				System.out.println(handlerService.canExecute(cmd));

				// execute the command
				handlerService.executeHandler(cmd);
			}
		});
	}

	public static void setNameCallback(NameCallback callback)
	{
		nameCallback = callback;
	}
}
