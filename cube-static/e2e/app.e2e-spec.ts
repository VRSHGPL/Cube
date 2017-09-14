import { CubePage } from './app.po';

describe('cube App', () => {
  let page: CubePage;

  beforeEach(() => {
    page = new CubePage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
