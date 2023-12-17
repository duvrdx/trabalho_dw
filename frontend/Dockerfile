# Use an official Node.js runtime as a base image
FROM node:18-alpine

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code to the working directory
COPY . .

# Expose the port that your app will run on (replace 3000 with the port used by your app)
EXPOSE 3000

# Build your Vue.js application (replace "npm run build" with the actual build command)
RUN npm run build

# Set the command to run your Vue.js application (replace "npm run serve" with the actual start command)
CMD ["npx", "vite", "preview", "--port", "3000", "--host"]

