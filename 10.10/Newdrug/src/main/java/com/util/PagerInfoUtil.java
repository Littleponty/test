package com.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.util.PagerInfo;

public class PagerInfoUtil {

	public static PagerInfo requestPage(HttpServletRequest request) {
		String draw = request.getParameter("draw");
		String length = request.getParameter("length");
		String orderDir = request.getParameter("order[0][dir]") == null ? "asc" : request.getParameter("order[0][dir]");
		String start = request.getParameter("start");
		String orderColumnNumber = request.getParameter("order[0][column]");
		String orderColumn = request.getParameter("columns[" + orderColumnNumber + "][data]");
		String searchValue = request.getParameter("search[value]");

		// 获取�?有可查询字段
		List<String> columns = new ArrayList<String>();
		int i = 0;
		while (i >= 0) {
			String searchable = request.getParameter("columns[" + i + "][searchable]");
			if (searchable == null) {
				break;
			}
			if (searchable.trim().equals("true")) {
				columns.add(request.getParameter("columns[" + i + "][data]"));
			}
			i++;
		}
		String[] searchColumns = new String[columns.size()];
		columns.toArray(searchColumns);

		// 处理不符合的字符�?
		if (!orderDir.equals("asc") && !orderDir.equals("desc")) {
			orderDir = "asc";
		}
		if (!orderColumn.matches("[A-Za-z0-9_]{0,64}")) {
			orderColumn = "";
		}

		PagerInfo pagerInfo = new PagerInfo(Integer.parseInt(draw), orderColumn, orderDir, Integer.parseInt(start),
				Integer.parseInt(length), searchColumns, searchValue);
		return pagerInfo;
	}

	public static String afterOrderBy(String hql, PagerInfo pagerInfo) {
		if (!pagerInfo.getOrderColumn().equals("")) {
			hql = hql + " order by " + pagerInfo.getOrderColumn() + " " + pagerInfo.getOrderDir();
		}
		return hql;
	}

	public static String addSearchCondition(String hql, PagerInfo pagerInfo) {
		if (pagerInfo.getSearchValue() == null || pagerInfo.getSearchValue().equals("")) {
			return hql;
		}
		if (pagerInfo.getSearchColumns() == null || pagerInfo.getSearchColumns().length == 0) {
			return hql;
		}
		if (hql.toLowerCase().indexOf("where") == -1) {
			hql += " where ";
		} else {
			hql += " and ";
		}
		hql += "(";
		for (int i = 0, n = pagerInfo.getSearchColumns().length; i < n; i++) {
			hql += pagerInfo.getSearchColumns()[i] + " like ?";
			if (i < n - 1) {
				hql += " or ";
			}
		}
		hql += ")";
		return hql;
	}

}
