import React, { Component } from "react"
import { observer } from "mobx-react"
import "./Console.scss"

@observer
class Console extends Component {
  render() {
    const store = this.props.store
    let button = (
      <input type="button" value="줄이기" onClick={store.createSURL} />
    )

    if (store.state === "done") {
      button = (
        <input
          type="button"
          value="복사"
          onClick={() => store.copyURL(document.getElementById("url"))}
        />
      )
    }

    return (
      <div className="console">
        <h2>Uterm Console</h2>
        <form id="surl">
          <input
            type="text"
            value={store.url}
            id="url"
            onChange={store.writeURL}
            placeholder="간소화할 URL을 입력해주세요."
          />
          {button}
          <p>{store.errorMessage}</p>
        </form>
      </div>
    )
  }
}

export default Console
