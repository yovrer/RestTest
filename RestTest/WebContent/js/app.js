var myApp = angular.module('myApp', []);

myApp.controller('mainController', function($scope, employeeServices) {
	$scope.allEmployees = [];
	$scope.employees = {};
	$scope.getAll = function() {
		employeeServices.getALlEmployees(function(data) {
			$scope.allEmployees = data;
		});
	};
	$scope.getAll();
	$scope.reset = function() {
		$scope.employees = {};
	};
	$scope.edit = function(index) {
		$scope.employees = $scope.allEmployees[index];
	};
	$scope.remove = function(index) {
		var emp = $scope.allEmployees[index];
		employeeServices.removeEployee(emp.id).then(function(data) {
			if (data) {
				alert("the employee " + emp.firstName + " deleted");
				$scope.allEmployees.splice(index, index);
			} else {
				alert("error occurred when delete employee");
				$scope.allEmployees.splice(index, index);
			}
		}, function(data) {
			console.log(data);
		});
	}
	$scope.submit = function() {
		if (!$scope.employees.id) {
			employeeServices.addStudant($scope.employees).then(
					function(data) {
						console.log(data);
						if (data) {
							$scope.getAll();
							alert("the employee " + $scope.employees.firstName
									+ " added");
							$scope.employees = {};
						} else {
							alert("error occurred when add employee");
						}
					}, function(data) {
						console.log(data);
					})
		} else {
			employeeServices.updateStudant($scope.employees).then(
					function(data) {
						console.log(data);
						if (data) {
							$scope.getAll();
							alert("the employee " + $scope.employees.firstName
									+ " updated");
							$scope.employees = {};
						} else {
							alert("error occurred when update employee");
						}
					}, function(data) {
						console.log(data);
					})
		}
	}

});