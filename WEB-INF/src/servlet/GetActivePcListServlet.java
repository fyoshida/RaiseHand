package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Pc;
import model.PcJson;
import servlet.helper.JsonHelper;

@WebServlet(urlPatterns = { "/v1/active-seats" })
//active-seatsの応答関数
public class GetActivePcListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");


		// アクティブなPCを取得
		List<Pc> pcList = listManager.getActivePc();


		// アクティブなPCの情報をJSON形式で出力
		PrintWriter out = resp.getWriter();
		String jsonText = JsonHelper.getJsonText(pcList);
		out.println(jsonText);
	}

}