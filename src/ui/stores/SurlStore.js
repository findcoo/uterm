import { observable, action } from "mobx"
import { AjaxResponse } from "rxjs"

const handleHTTPStatus = resp => {
  if (!resp.ok) throw resp.text()

  return resp.json()
}

class SurlStore {
  @observable url = ""
  @observable errorMessage = ""
  @observable state = "pending" // pending, done, error

  @action
  createSURL = () => {
    this.errorMessage = ""

    fetch("/surl", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ url: this.url })
    })
      .then(handleHTTPStatus)
      .then(data => this.createSURLSuccess(data))
      .catch(err => err.then(msg => this.createSURLFail(msg)))
  }

  @action.bound
  createSURLSuccess = ({ shortedURL }) => {
    this.url = shortedURL
    this.state = "done"
  }

  @action.bound
  createSURLFail = err => {
    this.errorMessage = err
    this.state = "error"
  }

  @action writeURL = event => (this.url = event.target.value)

  @action
  copyURL = input => {
    input.select()
    document.execCommand("Copy")
    this.state = "pending"
    this.url = ""
  }
}

export default SurlStore
