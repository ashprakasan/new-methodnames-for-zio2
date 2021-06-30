package fix

import scalafix.v1._
import scala.meta._

class NewMethodnamesForZio2 extends SemanticRule("NewMethodnamesForZio2") {

  //TODO : create a List with all old methodnames, and a map with the right mappings to new names.
  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case t @ q"halt" => Patch.renameSymbol(t.symbol, "failCause")
      case tree: Defn.Class => Patch.empty
    }.asPatch
  }
}
