package org.eclipse.e4.security.demo.handler;

import org.eclipse.e4.core.di.annotations.Execute;

public class LoginHandler
{
	@Execute
	public void execute()
	{
		System.out.println("Successfully executed command!!!");
	}
}
