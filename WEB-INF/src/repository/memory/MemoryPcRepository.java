package repository.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.entities.Pc;
import domain.valueobjects.IpAddress;
import repository.IPcRepository;

public class MemoryPcRepository implements IPcRepository{
	private static final String[][] pcData = {
			{"ics801","133.44.118.158","true" },
			{"ics802","133.44.118.159","true" },
			{"ics803","133.44.118.160","true" },
			{"ics804","133.44.118.161","true" },
			{"ics805","133.44.118.162","true" },
			{"ics806","133.44.118.163","true" },
			{"ics807","133.44.118.164","true" },
			{"ics808","133.44.118.165","true" },
			{"ics809","133.44.118.166","false"},
			{"ics810","133.44.118.167","true" },
			{"ics811","133.44.118.168","true" },
			{"ics812","133.44.118.169","true" },
			{"ics813","133.44.118.170","true" },
			{"ics814","133.44.118.171","true" },
			{"ics815","133.44.118.172","true" },
			{"ics816","133.44.118.173","true" },
			{"ics817","133.44.118.174","true" },
			{"ics818","133.44.118.175","true" },
			{"ics819","133.44.118.176","true" },
			{"ics820","133.44.118.177","true" },
			{"ics821","133.44.118.178","true" },
			{"ics822","133.44.118.179","true" },
			{"ics823","133.44.118.180","true" },
			{"ics824","133.44.118.181","true" },
			{"ics825","133.44.118.182","false"},
			{"ics826","133.44.118.183","true" },
			{"ics827","133.44.118.184","true" },
			{"ics828","133.44.118.185","true" },
			{"ics829","133.44.118.186","true" },
			{"ics830","133.44.118.187","true" },
			{"ics831","133.44.118.188","true" },
			{"ics832","133.44.118.189","true" },
			{"ics833","133.44.118.190","true" },
			{"ics834","133.44.118.191","true" },
			{"ics835","133.44.118.192","true" },
			{"ics836","133.44.118.193","true" },
			{"ics837","133.44.118.194","true" },
			{"ics838","133.44.118.195","true" },
			{"ics839","133.44.118.196","true" },
			{"ics840","133.44.118.197","true" },
			{"ics841","133.44.118.198","true" },
			{"ics842","133.44.118.199","true" },
			{"ics843","133.44.118.200","true" },
			{"ics844","133.44.118.201","true" },
			{"ics845","133.44.118.202","true" },
			{"ics846","133.44.118.203","true" },
			{"ics847","133.44.118.204","true" },
			{"ics848","133.44.118.205","true" },
			{"ics849","133.44.118.206","true" },
			{"ics850","133.44.118.207","true" },
			{"ics851","133.44.118.208","true" },
			{"ics852","133.44.118.209","true" },
			{"ics853","133.44.118.210","true" },
			{"ics854","133.44.118.211","true" },
			{"ics855","133.44.118.212","true" },
			{"ics856","133.44.118.213","true" },
			{"ics857","133.44.118.214","true" },
			{"ics858","133.44.118.215","true" },
			{"ics859","133.44.118.216","true" },
			{"ics860","133.44.118.217","true" },
			{"ics861","133.44.118.218","true" },
			{"ics862","133.44.118.219","true" },
			{"ics863","133.44.118.220","true" },
			{"ics864","133.44.118.221","true" },
			{"ics865","133.44.118.222","false"},
			{"ics866","133.44.118.223","false"},
			{"ics867","133.44.118.224","false"},
			{"ics868","133.44.118.225","false"},
			{"ics869","133.44.118.226","false"},
			{"ics870","133.44.118.227","false"},
			{"ics871","133.44.118.228","false"}
		};

	private final List<Pc> pcList=new ArrayList<Pc>();

	public  MemoryPcRepository()  {
	
		for (int i = 0; i < pcData.length; i++) {
			String hostName = pcData[i][0];
			IpAddress ipAddress = new IpAddress( pcData[i][1]);

			Pc pc = new Pc(ipAddress,hostName);
			pcList.add(pc);
		}
	}

	@Override
	public List<Pc> getPcList() {
		return Collections.unmodifiableList(pcList);
	}

}
