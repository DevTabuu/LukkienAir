export default class Client {

    makeRequest(uri){
        let result = new Promise((resolve, reject) => {
            let request = new XMLHttpRequest();
            request.open("GET", uri, true);
            request.onreadystatechange = (e) => {
                if(request.readyState !== 4){
                    return;
                }

               let raw = request.responseText;
               let objectified = [];
               try{
                objectified = JSON.parse(raw);
               } catch (ignore) {}
               
               resolve(objectified);
            }
            request.send();
        });

        return result;
    }

    flights(origin, destination, min, max, date){
        return this.makeRequest(`/api/flights?originId=${origin}&destinationId=${destination}&costMin=${min}&costMax=${max}&departureDate=${date}`);
    }

    destinations(){
        return this.makeRequest(`/api/destinations`);
    }

    getRouteFromId(id){
        return this.makeRequest(`/api/routes/${id}`);
    }

    getDestinationFromId(id){
        return this.makeRequest(`/api/destinations/${id}`);
    }
}