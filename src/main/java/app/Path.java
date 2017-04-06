package app;

public class Path {
	public static class Web {
		public static final String INDEX = "/index/";
		public static final String LOGIN = "/login/";
		public static final String LOGOUT = "/logout/";
		public static final String BETS = "/bets/";
	}
	public static class Template {
		public final static String INDEX = "/velocity/index/index.vm";
		public final static String LOGIN = "/velocity/login/login.vm";
		public final static String BETS = "/velocity/bets/all.vm";		
		public static final String NOT_FOUND = "/velocity/notFound.vm";
	}
}
