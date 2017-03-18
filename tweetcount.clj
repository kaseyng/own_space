(ns tweetcount
    (:use     [streamparse.specs])
    (:gen-class))

(defn tweetcount [options]
  [
    ;; spout configuration
    {"sentences-spout" (python-spout-spec
        options
        "spouts.sentences.Sentences"
        ["sentence"]
        )
    }
    ;; Bolts
    {"parse-bolt" (python-bolt-spec
        options
        {"sentence-spout" :shuffle}
        ;; bolt configuration 1
        "bolts.parse.ParseTweet"
        ["words"])
        ;; bolt configuration 2
    }
    {"count-bolt" (python-bolt-spec
          options
          {"words-spout" :shuffle}
        "bolts.tweetcounter.TweetCounter"
        ["word","count"])
    }
  ]
)
