/**
 *
 */
package org.eclipse.rap.e4.security.demo;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.e4.E4ApplicationConfig;
import org.eclipse.rap.e4.E4EntryPointFactory;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

/**
 * @author SHomeier
 */
public class RapApplication implements ApplicationConfiguration
{
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
