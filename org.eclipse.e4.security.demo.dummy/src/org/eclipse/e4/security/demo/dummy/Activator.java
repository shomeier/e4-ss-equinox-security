package org.eclipse.e4.security.demo.dummy;

import java.net.URL;

import javax.security.auth.login.LoginException;

import org.eclipse.equinox.security.auth.ILoginContext;
import org.eclipse.equinox.security.auth.LoginContextFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator
{
	public static final String BUNDLE_ID = "org.eclipse.e4.security.demo.dummy";
	private static final String JAAS_CONFIG_FILE = "data/jaas_config.txt";
	private static BundleContext bundleContext;

	public void start(BundleContext context) throws Exception
	{
		System.out.println("Activating Dummy Login Module Bundle!!!");
		bundleContext = context;
		String configName = "DUMMY";
		URL configUrl = context.getBundle().getEntry(JAAS_CONFIG_FILE);
		ILoginContext secureContext = LoginContextFactory.createContext(configName, configUrl);
		try
		{
			secureContext.login();
		}
		catch (LoginException exception)
		{
			Throwable cause = exception.getCause();
			if (cause != null && cause.getCause() instanceof ThreadDeath)
			{
				throw (ThreadDeath) cause.getCause();
			}
			// IStatus status = new Status(IStatus.ERROR, "org.eclipse.rap.security.demo", "Login failed", exception);
			// ErrorDialog.openError(null, "Error", "Login failed", status);
		}
		// Integer result = null;
		// Display display = PlatformUI.createDisplay();
		// try
		// {
		// result = (Integer) Subject.doAs(secureContext.getSubject(), getRunAction(display));
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// finally
		// {
		// display.dispose();
		// secureContext.logout();
		// }
		// return result;
	}

	public void stop(BundleContext context) throws Exception
	{
		bundleContext = context;
	}

	public static BundleContext getBundleContext()
	{
		return bundleContext;
	}
}
