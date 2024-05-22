const BASE_URL = process.env.REACT_APP_BACKEND_URL;

function CustomFetcher(endpoint, options) {
  const url = `${BASE_URL}${endpoint}`;

  return fetch(url, options)
    .then((response) => {
      return response.json().then((data) => ({
        status: response.status,
        data: data,
      }));
    })
    .catch((error) => {
      throw new Error(`Network error: ${error.message}`);
    });
}

// Function to make GET requests
function getRequest(endpoint, headers = {}) {
  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
  };

  return CustomFetcher(endpoint, options);
}

// Function to make POST requests
function postRequest(endpoint, data, headers = {}) {
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
    body: JSON.stringify(data),
  };

  return CustomFetcher(endpoint, options);
}

// Function to make PUT requests
function putRequest(endpoint, data, headers = {}) {
  const options = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
    body: JSON.stringify(data),
  };

  return CustomFetcher(endpoint, options);
}

// Function to make DELETE requests
function deleteRequest(endpoint, headers = {}) {
  const options = {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
  };

  return CustomFetcher(endpoint, options);
}

export { getRequest, postRequest, putRequest, deleteRequest };
