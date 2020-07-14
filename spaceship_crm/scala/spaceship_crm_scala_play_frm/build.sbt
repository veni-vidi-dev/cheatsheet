name := "spaceship_crm_scala_play_frm"
 
version := "1.0" 
      
lazy val `spaceship_crm_scala_play_frm` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  ehcache , ws , specs2 % Test , guice,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "org.postgresql" % "postgresql" % "42.2.2",
  "be.objectify" %% "deadbolt-scala" % "2.6.0",
  "org.mindrot" % "jbcrypt" % "0.4"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      