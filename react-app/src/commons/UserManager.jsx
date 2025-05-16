export function setHeaders() {

    const headers = new Headers();
    headers.append("Authorization", "Bearer " + sessionStorage.getItem("AccessToken"))
    headers.append("RefreshToken", localStorage.getItem("RefreshToken"))

    return headers;
    
}

export function getHeaders(res) {

    if(res.headers.get("AccessToken") != null) {
        sessionStorage.setItem("AccessToken", res.headers.get("AccessToken"))
    }

}