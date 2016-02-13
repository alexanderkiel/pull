# Pull

Pull like in Datomic but over maps of maps.

## Install

To install, just add the following to your project dependencies:

```clojure
[org.clojars.akiel/pull "0.1"]
```

## Usage

```clojure
(require 'pull.core)

(pull {:a 1 :b 2} [:a])
;; => {:a 1}
```

## License

Copyright © 2016 Alexander Kiel

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
