package network;

import javax.servlet.http.HttpServletRequest;

import repository.db.DataBaseRepository;
import repository.dummy.DummyRepository;
import repository.file.FileRepository;

public class NetworkFactory {
	public static String ipAddress = "133.44.118.158";
	public static String hostName = "ics801";

	public static NetworkType networkType = NetworkType.Dummy;

	public static INetwork getNetwork(HttpServletRequest request) {
		switch (networkType) {
		case Servlet:
			return new ServletNetwork(request);
		default:
			return new DummyNetwork(ipAddress,hostName);
		}
	}
}
