/**
 *
 */
package org.eclipse.e4.security.demo.login;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author SHomeier
 */
public class DummyCallbackHandler implements CallbackHandler
{
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
	{
		System.out.println("CallbackHandler successfully triggered!!!");

	}
}
