import React, { Component } from "react"
import { hot } from "react-hot-loader"
import DevTools from "mobx-react-devtools"

import Console from "./Console"
import SurlStore from "../stores/SurlStore"
import "./App.scss"

class App extends Component {
  render() {
    return (
      <div className="app">
        <Console store={new SurlStore()} />
        <DevTools />
      </div>
    )
  }
}

export default hot(module)(App)
