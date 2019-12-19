
var map;
var plotFunction;

function initMap() {
    map = new google.maps.Map(
        document.getElementById('map'),
        {center: new google.maps.LatLng(-33.91722, 151.23064), zoom: 1});

    // Create markers.
    plotFunction = function plotFunction(features) {
        for (var i = 0; i < features.length; i++) {
            var marker = new google.maps.Marker({
                position: features[i].position,
                icon: 'https://developers.google.com/maps/documentation/javascript/examples/full/images/info-i_maps.png',
                map: map
            });
        }
    }
}

$(document).ready(function () {
    // initMap();
    // let zipCode = $('#zipCode').val();
    // alert(zipCode);
        function getCoordinates(address) {
        fetch("https://maps.googleapis.com/maps/api/geocode/json?address=52557&key=AIzaSyCk-X9zQpYix4tLG82_42AWRtULmcF0QQA")
            .then(response => response.json())
            .then(data => {
                const latitude = data.results.geometry.location.lat;
                const longitude = data.results.geometry.location.lng;
                console.log({latitude, longitude})
            });
    }
    getCoordinates();
});



// var map;
//
// function initMap() {
//     map = new google.maps.Map(
//         document.getElementById('map'),
//         {center: new google.maps.LatLng(-33.91722, 151.23064), zoom: 1});
// }
//
// $(document).ready(function () {
//     let url = $('#mapUrl').val();
//     console.log(url);
//     console.log(' niyo birdman');
//     function getCoordinates(address) {
//         fetch(url)
//             .then(response => response.json())
//             .then(data => {
//                 const latitude = data.results.geometry.location.lat;
//                 const longitude = data.results.geometry.location.lng;
//                 console.log({latitude, longitude})
//             });
//     }
//
// });
