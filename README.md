# Henry's Grocery

## Tests

Here are some ideas for tests to write:

Basic pricing
- [x] Empty Cart
- [x] One product
- [x] Multiple instances of a product
- [x] Multiple products
- [x] Multiple instances of multiple products
- [x] Adding more of the first product after adding a different one.

Discounted pricing
- [x] No discounts. (Probably just implied by basic pricing.)
- [x] One active discount that applies to the basket.
- [ ] One active discount that does NOT apply to the basket.
- [ ] One pending discount
- [ ] One expired discount
- [ ] One active and one pending discount
- [ ] Multiple active discounts

Projections
- [ ] Today
- [ ] 2 days hence
- [ ] 5 days hence
- [ ] 35 days hence

## Other thoughts

- My gut says it would be better if the Discounts asked the Store
  for the price of a Product, rather than the basket. It's not
  immediately obvious how to push that change so I'll wait.