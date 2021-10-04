package jp.co.sss.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginCheckFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//ServletRequest 型のリクエスト情報を HttpServletRequest 型
//（サブクラスの型）にキャストする
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURL = httpRequest.getRequestURI();
		if (requestURL.endsWith("/loginWithValidation")) {
//リクエスト URL が「ログイン画面への遷移処理」、
//「ログイン処理」宛ての場合、ログインチェックを実施せず、
//リクエスト対象のコントローラの処理に移る
			chain.doFilter(request, response);
		} else {
//セッション情報を取得
			HttpSession session = httpRequest.getSession();
//セッション情報からユーザのログイン情報（ユーザ ID）を取得
			Integer userId = (Integer) session.getAttribute("userId");
			if (userId == null) {
//ログイン情報が存在しない場合（ログイン ID が null の場合）、
//ログイン画面にリダイレクトする
//ServletResponse 型のリクエスト情報を
//HttpServletResponse 型（サブクラスの型）にキャストする
				HttpServletResponse httpResponse = (HttpServletResponse) response;
//ログイン画面にリダイレクトする
				httpResponse.sendRedirect("/shop/loginWithValidation");
			} else {
				// ログイン情報が存在する場合
				// （ログイン ID に何らかの文字列が保存されている場合）、
				// リクエスト対象のコントローラの処理に移る
				chain.doFilter(request, response);
			}
		}
	}
}
