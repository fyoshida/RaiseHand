package repository;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.entities.Pc;

abstract public class RepositoryTest {
	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String IPADDRESS_NOTREGISTED_1 = "133.44.118.157";
	public static final String IPADDRESS_NOTREGISTED_2 = "133.44.118.229";

	public static final String IPADDRESS_REGISTED_1 = "133.44.118.158";
	public static final String IPADDRESS_REGISTED_2 = "133.44.118.228";

	public static final String HOSTNAME_REGISTED_1 = "ics801";
	public static final String HOSTNAME_REGISTED_2 = "ics871";

	protected InetAddress ipAddressGateWay;
	protected InetAddress ipAddressNotRegisted1;
	protected InetAddress ipAddressNotRegisted2;
	protected InetAddress ipAddressRegisted1;
	protected InetAddress ipAddressRegisted2;

	protected String hostNameGateWay="icsGateWay";

	protected IPcRepository repository;

	@Before
	public void setUp() {
		try {
			ipAddressGateWay = InetAddress.getByName(IPADDRESS_GATEWAY);
			ipAddressNotRegisted1 = InetAddress.getByName(IPADDRESS_NOTREGISTED_1);
			ipAddressNotRegisted2 = InetAddress.getByName(IPADDRESS_NOTREGISTED_2);
			ipAddressRegisted1 = InetAddress.getByName(IPADDRESS_REGISTED_1);
			ipAddressRegisted2 =InetAddress.getByName(IPADDRESS_REGISTED_2);
			
			initializeRepository();
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

	abstract public void initializeRepository();

	@Test
	public void Pc情報の総数が一致している() throws Exception {
		List<Pc> pcList = repository.getPcList();

		assertEquals(pcList.size(), 71);
	}

	@Test
	public void 登録されていないPcは取得できない() throws Exception {
		List<Pc> pcList = repository.getPcList();

		assertNull(getPc(pcList,ipAddressGateWay));
		assertNull(getPc(pcList,ipAddressNotRegisted1));
		assertNull(getPc(pcList,ipAddressNotRegisted2));
	}

	@Test
	public void 登録されているPcは取得できる() throws Exception {
		List<Pc> pcList = repository.getPcList();

		Pc pc1 = getPc(pcList,ipAddressRegisted1);
		assertTrue(pc1.getIpAddress().equals(ipAddressRegisted1));
		assertEquals(pc1.getHostName(),HOSTNAME_REGISTED_1);

		Pc pc2 = getPc(pcList,ipAddressRegisted2);
		assertTrue(pc2.getIpAddress().equals(ipAddressRegisted2));
		assertEquals(pc2.getHostName(),HOSTNAME_REGISTED_2);
	}

	protected Pc getPc(List<Pc> pcList,InetAddress ipAddress) {
		for(Pc pc: pcList) {
			if(pc.getIpAddress().equals(ipAddress)) {
				return pc;
			}
		}
		return null;
	}

	protected Pc getPc(List<Pc> pcList,String hostName) {
		for(Pc pc: pcList) {
			if(pc.getHostName().equals(hostName)) {
				return pc;
			}
		}
		return null;
	}
}
