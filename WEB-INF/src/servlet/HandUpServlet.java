package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.HelpStatus;
import model.IpAddress;
import model.Pc;
import model.PcManager;
import network.NetworkInterface;
import network.ServletNetwork;
import servlet.helper.JsonConverter;
import servlet.helper.NetworkHelper;
import servlet.helper.PcJson;
import servlet.helper.PcJsonConverter;

@WebServlet(urlPatterns = { "/v1/call/*" })
//call-teacher/XXXの応答関数
public class HandUpServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// PcManagerを取得
		ServletContext sc = getServletContext();
		PcManager pcManager=(PcManager)sc.getAttribute("PcManager");

		// クライアントIPアドレスの取得
		IpAddress ipAddress = NetworkHelper.getIpAddressWithServletNetwork(req);

		// クライアントPCを取得
		Pc pc = pcManager.getPc(ipAddress);

		if (pc == null) {
			req.getRequestDispatcher("/error.html").forward(req,resp);
			return;
		}

		// 手をあげる
		pc.handUp();

		//pcManagerを保存.
		sc.setAttribute("PcManager", pcManager);

		// 全PCを取得
		List<Pc> pcList = pcManager.getPcList();

		// Pc --> PcJson
		List<PcJson> pcJsonList=PcJsonConverter.getPcJson(pcList);

		// クライアントPCの情報をJSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonConverter.getJsonText(pcJsonList);
		out.println(jsonText);
	}


}