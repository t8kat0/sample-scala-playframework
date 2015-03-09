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
        + "\nqueryString: " + request.queryString
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

  def path_parameter(id: Long) = Action { request =>
    val message = "uri: " + request.uri    +
        "\nmethod: "      + request.method +
        "\npath: "        + request.path   +
        "\nqueryString: " + request.queryString
    println("[DEBUG] " + message)
    Ok("SampleController#path_parameter: id=" + id + "\n\n" + message)
  }

  /**
   * クエリパラメータを指定するアクション:
   *   GET http://localhost:9000/sample/query_parameter?id=1234 200(OK)
   *   GET http://localhost:9000/sample/query_parameter?id=abc  400(Bad Request)
   */
  def query_parameter(id: Long) = Action { request =>
    val message = "uri: " + request.uri    +
        "\nmethod: "      + request.method +
        "\npath: "        + request.path   +
        "\nqueryString: " + request.queryString
    println("[DEBUG] " + message)
    Ok("SampleController#query_parameter: id=" + id + "\n\n" + message)
  }

  /**
   * ルーティング設定にワイルドカードを指定することで任意(可変個)のパスパラメータを取得するアクション:
   *
   *    GET: http://localhost:9000/sample/wird_card/foo/+/1234/-/bar/
   *     HTTP-Status: 200(OK)
   *     path: "foo/+/1234/-/bar/"
   *
   *   GET: http://localhost:9000/sample/wird_card/
   *     HTTP-Status: 404(Not Found)
   */
  def wird_card(path: String) = Action { request =>
    val message = "uri: " + request.uri    +
        "\nmethod: "      + request.method +
        "\npath: "        + request.path   +
        "\nqueryString: " + request.queryString
    println("[DEBUG] " + message)
    Ok("SampleController#wird_card: path=" + path + "\n\n" + message)
  }

  /**
   * ルーティング設定に正規表現を指定することで任意のパスパラメータを取得するアクション:
   * 
   * GET: http://localhost:9000/sample/regex/1234
   *   HTTP-Status: 200(OK)
   *
   * GET: http://localhost:9000/sample/regex/
   *   HTTP-Status: 404(Not Found)
   *
   * GET: http://localhost:9000/sample/regex/abcd
   *   HTTP-Status: 404(Not Found)
   */
  def regex(id: Long) = Action { request =>
    val message = "uri: " + request.uri    +
        "\nmethod: "      + request.method +
        "\npath: "        + request.path   +
        "\nqueryString: " + request.queryString
    println("[DEBUG] " + message)
    Ok("SampleController#regex: id=" + id + "\n\n" + message)
  }

  def read = Action {
    Ok(views.html.index("SampleController#index"))
  }
}
