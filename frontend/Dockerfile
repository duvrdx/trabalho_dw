FROM node:18-alpine

workdir /app
COPY node_modules /app/node_modules
COPY dist /app/dist

EXPOSE 80

CMD ["npx", "vite", "preview", "--port", "80", "--host"]