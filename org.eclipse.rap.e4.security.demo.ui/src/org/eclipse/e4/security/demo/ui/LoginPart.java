package org.eclipse.e4.security.demo.ui;

import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.security.demo.handler.LoginHandler;
import org.eclipse.swt.SWT;
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

	private Text txt_user;

	private Text txt_password;

	@Inject
	public LoginPart(Composite parent, final ECommandService commandService, final EHandlerService handlerService)
	{
		// set grid layout to parent
		parent.setLayout(new GridLayout());

		Composite top = new Composite(parent, SWT.NONE);

		top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;
		layout.makeColumnsEqualWidth = true;// make each column have same width
		layout.numColumns = 2; // number of columns
		layout.verticalSpacing = 10;
		top.setLayout(layout);

		Label lbl_user = new Label(top, SWT.NONE);
		lbl_user.setText("User:");

		GridData gd_user = new GridData(SWT.FILL, SWT.CENTER, true, true);
		txt_user = new Text(top, SWT.BORDER);
		txt_user.setLayoutData(gd_user);

		Label lbl_password = new Label(top, SWT.NONE);
		lbl_password.setText("Password:");
		GridData gd_pw = new GridData(SWT.FILL, SWT.CENTER, true, true);
		txt_password = new Text(top, SWT.BORDER | SWT.PASSWORD);
		txt_password.setLayoutData(gd_pw);

		GridData gd_login = new GridData(SWT.FILL, SWT.CENTER, true, true);
		gd_login.horizontalSpan = 2;
		Button btn_login = new Button(top, SWT.NONE);
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
}
