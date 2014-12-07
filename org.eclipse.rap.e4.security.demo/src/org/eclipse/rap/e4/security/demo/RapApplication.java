/**
 *
 */
package org.eclipse.rap.e4.security.demo;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import org.eclipse.equinox.security.auth.ILoginContext;
import org.eclipse.equinox.security.auth.LoginContextFactory;
import org.eclipse.rap.e4.E4ApplicationConfig;
import org.eclipse.rap.e4.E4EntryPointFactory;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.osgi.service.component.ComponentContext;

/**
 * @author SHomeier
 */
public class RapApplication implements ApplicationConfiguration
{
	private static final String JAAS_CONFIG_FILE = "data/jaas_config.txt";

	protected void activate(ComponentContext context)
	{
		String configName = "DUMMY";
		URL configUrl = context.getBundleContext().getBundle().getEntry(JAAS_CONFIG_FILE);
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

	public void configure(Application application)
	{
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(WebClient.PAGE_TITLE, "e4 Security RAP Sample");
		application.addEntryPoint(
			"/rap",
			new E4EntryPointFactory(E4ApplicationConfig
				.create("platform:/plugin/org.eclipse.rap.e4.security.demo/Application.e4xmi")), properties);
		application.setOperationMode(OperationMode.SWT_COMPATIBILITY);
	}
}
