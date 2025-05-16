export function setHeaders() {

    const headers = new Headers();
    headers.append("Authorization", "Bearer " + sessionStorage.getItem("AccessToken"))
    headers.append("RefreshToken", localStorage.getItem("RefreshToken"))

    return headers;
    
}