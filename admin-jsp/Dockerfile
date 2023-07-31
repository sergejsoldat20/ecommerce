# Use the official Tomcat base image
FROM tomcat:latest

# Set the working directory inside the container
WORKDIR /usr/local/tomcat/webapps/

# Remove the default ROOT application
RUN rm -rf ROOT/*

# Copy your compiled JSP Maven 2 application (WAR file) to the container
COPY target/admin-jsp.war ROOT.war

# Expose the port that your application will be running on
EXPOSE 8080

# Start Tomcat and deploy your application
CMD ["catalina.sh", "run"]