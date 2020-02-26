This is a simple gateway application configured to route requests to some backend service. It has
a single page which submits POST requests to that same service. It also has a web filter which
tries to read form data off of the post before it is routed by the gateway.

The problem is, form POSTs hang until they timeout. It seems this is caused by reading the form
data before the request is routed by the gateway.

## To reproduce
1. Configure the gateway to point to some service where you can verify the requests are received. 
The default config should work if the link hasn't expired.
    ```yaml
    spring:
      cloud:
        gateway:
          routes:
            # See https://webhook.site/#!/ff36f3af-5499-428d-96b2-f0f49469c5e3
            - id: example
              uri: https://webhook.site
              predicates:
                - Path=/**
              filters:
                - PrefixPath=/ff36f3af-5499-428d-96b2-f0f49469c5e3
    ```
   > Note: If using a url with a path, include it in a PrefixPath filter as shown above.
2. Run the gateway.
3. Load the Gateway webpage at `http://localhost:9999`.
4. There are two examples of POST requests to the Rest API, a form POST and an AJAX POST. Try
submitting both.

### Expected Behavior
Both requests should work.

### Actual Behavior
The AJAX POST works. 
The form POST hangs.
