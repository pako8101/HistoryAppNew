// const articlesList = document.getElementById('articlesList')
// const searchBar = document.getElementById('searchInput')
//
// const allArticles = [];
//
// fetch("http://localhost:1032/articles/api").
// then(response => response.json()).
// then(data => {
//   for (let article of data) {
//     allArticles.push(article);
//   }
// })
//
// console.log(allArticles);
//
// searchBar.addEventListener('keyup', (e) => {
//   const searchingCharacters = searchBar.value.toLowerCase();
//   let filteredArticles = allArticles.filter(article => {
//     return article.title.toLowerCase().includes(searchingCharacters)
//         || article.author.toLowerCase().includes(searchingCharacters);
//   });
//   displayArticles(filteredArticles);
// })
//
//
// const displayArticles = (articles) => {
//   articlesList.innerHTML = articles
//       .map((a) => {
//         return ` <div class="col-md-3" >
//                 <div class="card mb-4 box-shadow">
//                 <img src="${a.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
//                      data-holder-rendered="true"
//                      style="height: 225px; width: 100%; display: block;">
//                 <div class="card-body">
//                     <div class="text-center">
//                         <p class="card-text border-bottom ">Name: ${a.title}</p>
//                         <p class="card-text border-bottom ">Author: ${a.author}</p>
//                        <p class="card-text border-bottom ">Title: </p>
//                         <p class="card-text border-bottom ">Content: </p>
//                         <p class="card-text border-bottom">Period: </p>
//                     </div>
//                     <div class="d-flex justify-content-between align-items-center">
//
//                         <div class="btn-group">
//                             <a href="/albums/details/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">Details</a>
//                         </div>
//                         <div class="btn-group">
//                             <a href="/albums/delete/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">Delete</a>
//                         </div>
//
//                     </div>
//                 </div>
//             </div>
//             </div>`
//       })
//       .join('');
//
// }
import React, { useState } from 'react';
import axios from 'axios';

const SearchBar = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [articles, setArticles] = useState([]);

  // Функция за извършване на търсене
  const searchArticles = async (query) => {
    try {
      const response = await axios.get(`/api/articles/search?q=${query}`);
      setArticles(response.data);
    } catch (error) {
      console.error("Грешка при търсене:", error);
    }
  };

  const handleSearchChange = (e) => {
    const value = e.target.value;
    setSearchTerm(value);
    searchArticles(value); // Извършване на заявката при промяна в търсенето
  };

  return (
      <div>
        <input
            type="text"
            placeholder="Търси статия..."
            value={searchTerm}
            onChange={handleSearchChange} // Актуализира търсенето и прави заявка
        />
        <ul>
          {articles.length > 0 ? (
              articles.map(article => (
                  <li key={article.id}>{article.title}</li>
              ))
          ) : (
              <li>Няма резултати</li>
          )}
        </ul>
      </div>
  );
};

export default SearchBar;
