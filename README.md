# Drink Mall

[English](./README.md) | [简体中文](./README.zh-CN.md)

A full-stack e-commerce demo focused on drinks and beverages.

## Features

- **User module**: user registration/login, shipping address management, client IP retrieval, reviews, and order display.
- **Product module**: product details, product image display, SKU list, and paginated product reviews.
- **Homepage**: category & recommendation sections, new arrivals, banner carousel, fuzzy product search, and global navigation bar.
- **Shopping cart**: rich list display and operations.
- **Alipay payment**: QR-code payment, scheduled order status updates, and sales/inventory synchronization.
- **Merchant backend**: product management (including adding products).

## Tech Stack

This project uses a front-end/back-end separation architecture:

- **Backend**: Spring Boot
- **Frontend**: Vue + Element UI (in `vue/`)
- **Database**: MySQL
- **ORM & SQL**: MyBatis-Plus + MyBatis (with MyBatis Generator for entity and SQL generation)

## Project Structure

- `src/`: backend source code
- `vue/`: frontend project

## Getting Started

### 1) Start the frontend

```bash
cd vue
npm install
npm run serve
```

You can configure the backend base URL in:

- `vue/public/urlConfig.js`

### 2) Build and run the backend

From the project root (same level as `src`):

```bash
mvn clean package -DskipTests
```

Before running, update these settings as needed:

- Database connection and file upload path in `application.yml`
- Alipay public/private key config in `AlipayClientConfig.java`
- Payment callback URL and return page in `OrdersController.java`

### 3) Static resource/image path notes

Most frontend image links are served from backend path `/Img`.

- You can adjust backend mapping in `WebConfig.java`
- Frontend can also switch to local image loading, for example:

```vue
:src="require('../img' + variable)"
```

(where `img` is inside the Vue project)

## Contributing

1. Fork this repository
2. Create a feature branch (e.g. `feat_xxx`)
3. Commit your changes
4. Open a Pull Request
