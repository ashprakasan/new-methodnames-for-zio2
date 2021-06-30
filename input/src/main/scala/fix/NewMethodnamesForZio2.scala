/*
rule = NewMethodnamesForZio2
*/
package fix

object NewMethodnamesForZio2 {

  // Add code that needs fixing here.
  object zio1 {

    sealed trait Exit[+E, +A] {

    }

    sealed trait Cause[+E] {

    }

    object Exit {

      final case class Success[+A](value: A) extends Exit[Nothing, A]

      final case class Failure[+E](cause: Cause[E]) extends Exit[E, Nothing]

      def succeed[E, A](value: A): Exit[E, A] = ???

      def halt[E, A](cause: Cause[E]): Exit[E, A] = ???

      def failCause[E, A](cause: Cause[E]): Exit[E, A] = ???
    }

  }

  object Exit1 {

    import zio1._
    import zio1.Exit._
    import zio1.Cause

    def zipWith[E, A, B, C](a: Exit[E, A], b: Exit[E, B])(
      f: (A, B) => C,
      g: (Cause[E], Cause[E]) => Cause[E]
    ): Exit[E, C] =
      (a, b) match {
        case (Success(a), Success(b)) => Exit.succeed(f(a, b))
        case (Failure(l), Failure(r)) => Exit.halt(g(l, r))
        case (e@Failure(_), _) => e
        case (_, e@Failure(_)) => e
      }
  }

}
