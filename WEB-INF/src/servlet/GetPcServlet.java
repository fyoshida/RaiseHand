package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IpAddress;
import model.Student;
import model.StudentManager;
import servlet.helper.JsonConverter;
import servlet.helper.NetworkHelper;
import servlet.helper.PcJson;
import servlet.helper.PcJsonConverter;

@WebServlet(urlPatterns = { "/v1/whoami" })
//whoamiの応答関数
public class GetPcServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// StudentManagerを取得
		ServletContext sc = getServletContext();
		StudentManager pcManager = (StudentManager) sc.getAttribute("StudentManager");

		// クライアントIPアドレスの取得
		IpAddress ipAddress = NetworkHelper.getIpAddressWithServletNetwork(req);

		// 学生を取得
		Student student = pcManager.getStudent(ipAddress);
		if (student == null) {
			req.getRequestDispatcher("/error.html").forward(req, resp);
			return;
		}

		// Student --> PcJson
		PcJson pcJson = PcJsonConverter.getPcJson(student);

		// JSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJson);
		out.println(jsonText);
	}

}