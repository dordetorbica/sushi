package app;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import app.db.Db;
import app.index.IndexController;

public class Main {

	public static void main(String[] args) {
		Db db = new Db();
		//db.TestDB();
		staticFiles.location("/public");
		staticFiles.expireTime(600L);
		get("/", IndexController.serveIndexPage);
		get(Path.Web.INDEX, IndexController.serveIndexPage);
		enableDebugScreen();
	}

}
