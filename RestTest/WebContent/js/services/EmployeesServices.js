angular.module('myApp').service("employeeServices",function($http,$q){
	this.getALlEmployees = function (cb){
		$http({
			method:"GET",
			url:"http://localhost:8080/RestTest/getAllEmployees"
		}).then(
				function (data){
					cb(data.data);
				},
				function (data){
					console.log(data);
				}
		);
	}
	this.removeEployee = function (id){
		var dreffer = $q.defer();
		$http({
			method:"GET",
			url:"http://localhost:8080/RestTest/deleteEmployee",
			params:{"id":id}
		}).then(
				function (data){
					dreffer.resolve(data.data.state);
				},
				function (data){
					dreffer.reject(data.status);
				}
		);
		return dreffer.promise;
	}
	this.addStudant  = function (obj){
		var defer = $q.defer();
		$http({
			method:"POST",
			url:"http://localhost:8080/RestTest/addEmployee",
			params : obj
		}).then(function(data){
			defer.resolve(data.data.state);
		},
		function (data){
			defer.reject(data.massage);
		})
	return defer.promise;
	}
	this.updateStudant  = function (obj){
		var defer = $q.defer();
		$http({
			method:"POST",
			url:"http://localhost:8080/RestTest/updateEmployee",
			params : obj
		}).then(function(data){
			defer.resolve(data.data.state);
		},
		function (data){
			defer.reject(data.massage);
		})
	return defer.promise;
	}
});