package repository.dummy;

import java.util.List;

import beans.Pc;
import repository.RepositoryInterface;

public class DummyRepository implements RepositoryInterface{
	final private List<Pc> pcList;
	
	public DummyRepository(List<Pc> pcList) {
		this.pcList=pcList;
	}
	
	@Override
	public List<Pc> getPcList() {
		return pcList;
	}

	@Override
	public void updatePc(Pc pc) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
