package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pc;
import model.StudentManager;
import repository.RepositoryInterface;
import repository.dummy.DummyRepository;

@WebServlet(urlPatterns = { "/v1/initialize" })
//active-seatsの応答関数
public class InitializeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 全PC情報を取得
//		RepositoryInterface repository = new FileRepository("/WEB-INF/data/pcIdTable.csv");
		RepositoryInterface repository = new DummyRepository();

		try {
			// Pcの取得
			List<Pc> pcList = repository.getPcList();

			// StudentManagerを生成
			StudentManager studentManager=new StudentManager(pcList);

			// StudentManagerをServletContextに保存
			ServletContext sc = getServletContext();
			sc.setAttribute("StudentManager", studentManager);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 開始用Servletへ移動
//		req.getRequestDispatcher("GetAllPcServlet").forward(req, resp);
	}

}