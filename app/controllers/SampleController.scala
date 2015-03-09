package controllers

import play.api._
import play.api.mvc._

object SampleController extends Controller {

  /**
   * プレーンテキストを返却するアクション:
   *   GET http://localhost:9000/sample/hello 200(OK)
   */
  def hello = Action {
    // Response-Head:
    //   Content-Length: 27
    //   Content-Type: text/plain; charset=utf-8
    //
    // Response-Body:
    //   Hello, Playframework/Scala.
    Ok("Hello, Playframework/Scala.")
  }

  /**
   * リクエストの内容をプレーンテキストで返却するアクション:
   *   GET http://localhost:9000/sample/echo 200(OK)
   */
  def world = Action { request =>
    // printlnの結果は"activator run"を実行したコンソールに出力される
    println("[DEBUG] request=" + request)

    // Response-Body:
    //   uri: /sample/world
    //   method: GET
    //   path: /sample/world
    //   queryString: Map()  <--- クエリパラメータが指定されている場合は, そのキーと値が出力される
    Ok(
        "uri: " + request.uri
        + "\nmethod: " + request.method
        + "\npath: " + request.path
        + "\nqueryString: " + request.queryString.toString
    )
  }

  /**
   * リダイレクトするアクション:
   *   GET http://localhost:9000/sample/redirect 303(See Other)
   */
  def redirect = Action {
    // 以下のURLにリダイレクト:
    //   GET http://localhost:9000/sample/hello
    Redirect("/sample/hello")
  }

  /**
   * 未実装なアクション:
   *   GET http://localhost:9000/sample/unimplemented 501(Not Implemented)
   */
  def unimplemented = TODO

  def read = Action {
    Ok(views.html.index("SampleController#index"))
  }
}
