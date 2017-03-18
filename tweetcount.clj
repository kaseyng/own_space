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
        {"sentences-spout" :all}
        ;; bolt configuration 1
        "bolts.parse.ParseTweet"
        ["words"]
        :p 2)
        ;; bolt configuration 2
    }
    {"count-bolt" (python-bolt-spec
          options
          {"parse-blot" :all}
        "bolts.tweetcounter.TweetCounter"
        ["word","count"])
    }
  ]
)
