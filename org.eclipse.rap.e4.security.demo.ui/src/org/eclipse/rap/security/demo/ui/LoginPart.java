package org.eclipse.rap.security.demo.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LoginPart
{

	private Text txt_user;

	private Text txt_password;

	@Inject
	public LoginPart(Composite parent)
	{
		Composite composite_user = new Composite(parent, SWT.NONE);

		// Display display = Display.getCurrent();
		// composite.setBackground(display.getSystemColor(SWT.COLOR_BLUE));

		composite_user.setLayout(new RowLayout(SWT.HORIZONTAL));
		Label lbl_user = new Label(composite_user, SWT.NONE);
		lbl_user.setText("User:");
		txt_user = new Text(composite_user, SWT.BORDER);

		Composite composite_password = new Composite(parent, SWT.NONE);
		composite_user.setLayout(new RowLayout(SWT.HORIZONTAL));
		Label lbl_password = new Label(composite_password, SWT.NONE);
		lbl_password.setText("Password:");
		txt_password = new Text(composite_password, SWT.HORIZONTAL);

		Button datumButton = new Button(composite_user, SWT.NONE);
		datumButton.setText("Datum");
		datumButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				txt_user.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
			}
		});

		Button zeitButton = new Button(composite_user, SWT.PUSH);
		zeitButton.setText("Zeit");
		zeitButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				txt_user.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		});

	}
}
