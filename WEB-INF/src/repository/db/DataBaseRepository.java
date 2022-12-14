package repository.db;

import java.sql.ResultSet;
import java.util.List;

import model.IpAddress;
import model.Pc;
import repository.IRepository;

public class DataBaseRepository extends DataBaseManager implements IRepository{

	public DataBaseRepository(String dataBaseName,String userName,String passWord) {
		super(dataBaseName,userName,passWord);
	}

	public Pc copyRecord(ResultSet rs) throws Exception{

		// ------PC 情報------
		String hostName = rs.getString("HostName");
		String ipAddressString = rs.getString("IpAddress");
		boolean isStudent = rs.getBoolean("IsStudent");

		// ------IpAddress------
		IpAddress ipAddress =new IpAddress(ipAddressString);

		// ------PCオブジェクトに代入------
		Pc pc= new Pc(ipAddress,hostName);
		pc.setStudent(isStudent);

		return pc;
	}

	@Override
	public List<Pc> getPcList() {
		return (List<Pc>)getRecords("select * from pc");
	}
}
