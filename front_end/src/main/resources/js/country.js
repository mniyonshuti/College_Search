
var map;
var longitude;
var latitude;

function initMap() {
    map = new google.maps.Map(
        document.getElementById('map'),
        {center: new google.maps.LatLng(latitude, longitude), zoom: 12});
}

$(document).ready(function () {
    let zipCode = document.getElementById('zipCode').value;
    alert(zipCode);
        function getCoordinates(zipCode) {
        fetch("https://maps.googleapis.com/maps/api/geocode/json?address=52557&key=AIzaSyCk-X9zQpYix4tLG82_42AWRtULmcF0QQA")
            .then(response => response.json())
            .then(data => {
                latitude = data.results.geometry.location.lat;
                longitude = data.results.geometry.location.lng;
                console.log({latitude, longitude})
            });
    }
    getCoordinates();
    // initMap();
});

