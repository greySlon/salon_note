export default class $$restapi {

  doGetRequest(callback, url) {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials
    xhr.open('GET', url, true)
    xhr.onload = function () {
      if (xhr.readyState == 4 && xhr.status == "200") {
        var result = JSON.parse(xhr.responseText);
        callback(result);
      } else {
        console.error(result);
      }
    }
    xhr.send(null);
  }

  doPostRequest(obj, url, parametrized, callback) {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials;
    xhr.open('POST', url, true);
    xhr.onload = function () {
      if (xhr.readyState == 4 && xhr.status == "200") {
        if (callback) {
          callback(xhr.responseText);
        }
      } else {
        console.error(result);
      }
    }
    if (parametrized) {
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
      xhr.send(obj);
    } else {
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.send(JSON.stringify(obj));
    }
  }
}
