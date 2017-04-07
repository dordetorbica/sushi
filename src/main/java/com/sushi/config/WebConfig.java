package com.sushi.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import static spark.Spark.after;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.sushi.model.LoginResult;
import com.sushi.model.Bet;
import com.sushi.model.User;
import com.sushi.service.impl.SushiService;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.StringUtils;

public class WebConfig {

	private static final String USER_SESSION_ID = "user";
	private SushiService service;

	public WebConfig(SushiService service) {
		this.service = service;
		staticFileLocation("/public");
		setupRoutes();
	}

	private void setupRoutes() {
		/*
		 * Shows a users timeline or if no user is logged in, it will redirect
		 * to the public timeline. This timeline shows the user's messages as
		 * well as all the messages of followed users.
		 */
		get("/bets", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			List<Bet> bets = service.getAllBets();
			List<Map<String, Object>> data = new ArrayList<>();
			for (Bet bet : bets) {
				if (bet.getChallenger_id() > 0) {
					HashMap<String, Object> hm = new HashMap<>();
					int initiator_id = bet.getInitiator_id();
					int challenger_id = bet.getChallenger_id();
					String initiator = service.getUserbyId(initiator_id).getName();
					String challenger = service.getUserbyId(challenger_id).getName();
					hm.put("title", bet.getTitle());
					hm.put("bet_id", bet.getBet_id());
					hm.put("description", bet.getDescription());
					if (bet.getDescription() == null) {
						hm.put("description",  "");
					}
					hm.put("initiator_id", Integer.toString(initiator_id));
					hm.put("challenger_id", Integer.toString(challenger_id));
					hm.put("initiator", initiator);
					hm.put("challenger", challenger);
					data.add(hm);
				}
			}
			map.put("bets", data);
			return new ModelAndView(map, "openbets.ftl");
		}, new FreeMarkerEngine());
		get("/add-bet", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User authUser = getAuthenticatedUser(req);
			if (authUser == null) {
				res.redirect("/login");
				return null;
			}
			return new ModelAndView(map, "add-bet.ftl");
		}, new FreeMarkerEngine());
		post("/add-bet", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if (authUser == null) {
				res.redirect("/login");
				return null;
			}
			Bet bet = new Bet();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(bet, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			bet.setInitiator_id(authUser.getId());
			service.placeBet(bet);
			res.redirect("/bets");
			return null;
		}, new FreeMarkerEngine());
		get("/edit-bet", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			int id = Integer.parseInt(req.queryParams("id"));
			Bet bet = service.getBetbyId(id);
			HashMap<String, Object> hm = new HashMap<>();
			int initiator_id = bet.getInitiator_id();
			int challenger_id = bet.getChallenger_id();
			String initiator = service.getUserbyId(bet.getInitiator_id()).getName();
			String challenger = service.getUserbyId(bet.getChallenger_id()).getName();
			hm.put("title", bet.getTitle());
			hm.put("description", bet.getDescription());
			hm.put("bet_id", bet.getBet_id());
			hm.put("initiator_id", Integer.toString(initiator_id));
			hm.put("challenger_id", Integer.toString(challenger_id));
			hm.put("initiator", initiator);
			hm.put("challenger", challenger);
			map.put("bet", hm);
			return new ModelAndView(map, "edit-bet.ftl");
		}, new FreeMarkerEngine());
		get("/", (req, res) -> {
			res.redirect("/bets");
			return null;
		}, new FreeMarkerEngine());
		/*
		 * Presents the login form or redirect the user to her timeline if it's
		 * already logged in
		 */
		get("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			if (req.queryParams("r") != null) {
				map.put("message", "You were successfully registered and can login now");
			}
			return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());
		before("/", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});

		/*
		 * Displays the latest messages of all users.
		 */
		get("/public", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Public Timeline");
			map.put("user", user);
			List<Bet> messages = service.getAllBets();
			map.put("messages", messages);
			return new ModelAndView(map, "timeline.ftl");
		}, new FreeMarkerEngine());
		

		/*
		 * Presents the login form or redirect the user to her timeline if it's
		 * already logged in
		 */
		get("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			if (req.queryParams("r") != null) {
				map.put("message", "You were successfully registered and can login now");
			}
			return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());
		/*
		 * Logs the user in.
		 */
		post("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			LoginResult result = service.checkUser(user);
			if (result.getUser() != null) {
				addAuthenticatedUser(req, result.getUser());
				res.redirect("/");
				halt();
			} else {
				map.put("error", result.getError());
			}
			map.put("username", user.getUsername());
			return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/login", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if (authUser != null) {
				res.redirect("/");
				halt();
			}
		});

		/*
		 * Presents the register form or redirect the user to her timeline if
		 * it's already logged in
		 */
		get("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			return new ModelAndView(map, "register.ftl");
		}, new FreeMarkerEngine());
		/*
		 * Registers the user.
		 */
		post("/register", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8");
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			String error = user.validate();
			if (StringUtils.isEmpty(error)) {
				User existingUser = service.getUserbyUsername(user.getUsername());
				if (existingUser == null) {
					service.registerUser(user);
					res.redirect("/login?r=1");
					halt();
				} else {
					error = "The username is already taken";
				}
			}
			map.put("error", error);
			map.put("username", user.getUsername());
			map.put("email", user.getEmail());
			return new ModelAndView(map, "register.ftl");
		}, new FreeMarkerEngine());
		/*
		 * Checks if the user is already authenticated
		 */
		before("/register", (req, res) -> {
			User authUser = getAuthenticatedUser(req);
			if (authUser != null) {
				res.redirect("/");
				halt();
			}
		});

		/*
		 * Logs the user out and redirects to the public timeline
		 */
		get("/logout", (req, res) -> {
			removeAuthenticatedUser(req);
			res.redirect("/public");
			return null;
		});

		after((request, response) -> {
			response.header("Content-Encoding", "gzip");
		});

	}

	private void addAuthenticatedUser(Request request, User u) {
		request.session().attribute(USER_SESSION_ID, u);

	}

	private void removeAuthenticatedUser(Request request) {
		request.session().removeAttribute(USER_SESSION_ID);

	}

	private User getAuthenticatedUser(Request request) {
		return request.session().attribute(USER_SESSION_ID);
	}
}
