(ns tweetcount
    (:use     [streamparse.specs])
    (:gen-class))

(defn tweetcount [options]
  [
    ;; spout configuration
    {"sentences-spout" (python-spout-spec
        options
        "spouts.sentences.Sentences"
        ["sentences"]
        )
    }
    ;; Bolts
    {"count-bolt" (python-bolt-spec
        options
        {"sentences-spout" :shuffle}
        ;; bolt configuration 1
        "bolts.parse.ParseTweet")
            ...
        ;; bolt configuration 2
        (python-bolt-spec
          options
          {"sentences-spout" :shuffle}
        "bolts.tweetcounter.TweetCounter")
            ...
    }
  ]
)
