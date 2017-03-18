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
        {"sentences-spout" :shuffle}
        ;; bolt configuration 1
        "bolts.parse.ParseTweet"
        ["valid_words"]
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
