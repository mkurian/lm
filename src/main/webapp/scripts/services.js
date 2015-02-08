angular.module('localmarketApp')
    .factory('ReferService', ['$http', '$q', function($http, $q) {
    var message;
    var user = {};
    var referred = false;
    var referSuccess = function(resp) {
      name = resp.data.name;
      referred = true;
      return name;
    };
    var referFailure = function(err) {
      console.log('failure');
      referred = false;
      return $q.reject(err.data);
    };
    return {
      refer: function(data) {
        return $http.post('http://54.149.156.200:8080/localmarket/ads', 
          {name: data.name, age: data.age, location: data.location, city: data.city, job: data.job, contact: data.contact})
        .then(referSuccess, referFailure);
      }
    };
  }])
  .factory('SearchService', ['$http', '$q', function($http, $q) {
    var message;
    self.searchResults = [];
    
    var searchSuccess = function(resp) {
      self.searchResults = resp.data;
      return self.searchResults;
    };
    var searchFailure = function(err) {
      return $q.reject(err.data);
    };

    return {
      search: function(keyword) {
        return $http.get('http://54.149.156.200:8080/localmarket/ads',
        {
        params: {
            location: keyword
        }
      })
        .then(searchSuccess, searchFailure);
      }
    };
  }])
  .factory('ListService', ['$http', '$q', function($http, $q) {
     return {
      list: function() {
        return self.searchResults;
      }
    };
  }])
  .factory('SingleService', ['$http', '$q', function($http, $q) {
    self.ad = {};
     return {
      get: function() {
        return self.ad;
      },
      set: function(ad) {
        return self.ad = ad;
      }
    };
  }])

.factory('InterestService', ['$http', '$q',  function($http, $q) {
  self.locationdata = [];

   var loadSuccess = function(resp) {
      resp.data.forEach(function(d){
        self.locationdata.push(d.Location + " " + d.District + " (" + d.Pincode + ")");
      });
      return self.locationdata;
    };
    var loadFailure = function(err) {
      return $q.reject(err.data);
    };
    
     var submitSuccess = function(resp) {
      return;
    };
    var submitFailure = function(err) {
      return $q.reject(err.data);
    };
    
    return {
      loadLocations : function(){
        $http.get('data/pincodes.json').then(loadSuccess, loadFailure);
      },
      submit : function(buy, sell, items, email, location){
        return $http.post('http://54.149.156.200:8080/api/interest', 
          {buy: buy, sell: sell, location: location, email:email, items:items})
        .then(submitSuccess, submitFailure);
      }
    };
  }])
  ;