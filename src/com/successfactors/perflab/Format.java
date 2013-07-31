package com.successfactors.perflab;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.*;

/**
 * Servlet implementation class Format
 */
@WebServlet(description = "format the raw data", urlPatterns = { "/Format" })
public class Format extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Format() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("rawdata");
		List<String> temp = new ArrayList<String>();
	//	List<String> temp2 = new ArrayList<String>();
		if (str.startsWith("[") && str.endsWith("]")) {
			str = str.substring(1, str.lastIndexOf("]"));
			String target = str.substring(0, 10);// "2013-07-27";
			temp = formatString(str, target, temp);
			ComparatorDate comparator = new ComparatorDate();
			Collections.sort(temp, comparator);
//			for(String s:temp){
//				temp2.add(s);
//				temp2.add("_______________________________________________");
//			}
		} else {
			temp.add("please input the correct data!!!");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(JSON.toJSONString(temp));
		pw.flush();
	}

	public static List<String> formatString(String source, String target,
			List<String> list) {
		int i = 0;
		int j = 0;
		while ((i = source.indexOf(target, ++j)) != -1) {
			if (j != 0) {
				list.add(source.substring(--j, i));
				j = i;
			}
			i++;
		}
		list.add(source.substring(--j));
		return list;
	}
}
