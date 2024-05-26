import useToken from '../../stores/useToken';
import React from 'react';
import { useLocation } from 'react-router-dom';

const RepairFetchedData = () => {
    const { token } = useToken();

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const dataString = searchParams.get('data');
    const data = dataString ? JSON.parse(decodeURIComponent(dataString)) : null;
    console.log(data);
    return (
        <div style={{ textAlign: 'center', fontSize: '20px', margin: '20px' }}>
            <h1 style={{ marginTop: '80px', marginBottom: '20px' }}>Repairs</h1>
            {data && (
                <ul style={{ listStyleType: 'none', padding: 0 }}>
                    {data.map((repair, index) => (
                        <li key={index} style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc', borderRadius: '5px' }}>
                            {/* Render individual repair details here */}
                            <p><strong>RepairId:</strong> {repair.repairId}</p>
                            <p><strong>Local Date:</strong> {repair.localDate}</p>
                            <p><strong>Short Description:</strong> {repair.shortDescription}</p>
                            <p><strong>Type of Repair:</strong> {repair.typeOfRepair}</p>
                            <p><strong>Status of Repair:</strong> {repair.statusOfRepair}</p>
                            <p><strong>Cost:</strong> {repair.cost}</p>
                            <p><strong>Description Text:</strong> {repair.descriptionText}</p>
                            <p><strong>Property ID:</strong> {repair.propertyId}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default RepairFetchedData;



// import React from 'react';
// import { useLocation } from 'react-router-dom';

// const RepairFetchedData = () => {
//   const location = useLocation();
//   const searchParams = new URLSearchParams(location.search);
//   const dataString = searchParams.get('data');
//   const data = dataString ? JSON.parse(decodeURIComponent(dataString)) : null;

//   return (
//     <div>
//       <h1>My Repairs</h1>
//       {data && (
//         <ul>
//           {data.map((repair, index) => (
//             <li key={index}>
//               {/* Render individual repair details here */}
//              <p>Local Date: {repair.localDate}</p>
//              <p>Short Description: {repair.shortDescription}</p>
//              <p>Type of Repair: {repair.typeOfRepair}</p>
//              <p>Status of Repair: {repair.statusOfRepair}</p>
//              <p>Cost: {repair.cost}</p>
//              <p>Description Text: {repair.descriptionText}</p>
//              <p>Property ID: {repair.propertyId}</p>
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default RepairFetchedData;


// import React, { useEffect, useState } from 'react';
// import { useLocation } from 'react-router-dom';
// import axios from 'axios';

// const RepairFetchedData = ({ token }) => {
//   const location = useLocation();
//   const searchParams = new URLSearchParams(location.search);
//   const dataString = searchParams.get('data');
//   const data = dataString ? JSON.parse(decodeURIComponent(dataString)) : [];

//   // State to store the fetched repair data
//   const [repairs, setRepairs] = useState([]);
//   const [isLoading, setIsLoading] = useState(true);

//   useEffect(() => {
//     const fetchRepairs = async () => {
//       try {
//         // Make API request to fetch repair data using the token
//         const response = await axios.get('/api/repairs', {
//           headers: {
//             Authorization: `Bearer ${token}`,
//           },
//         });
//         setRepairs(response.data);
//       } catch (error) {
//         console.error('Error fetching repairs:', error);
//       } finally {
//         setIsLoading(false);
//       }
//     };

//     fetchRepairs();
//   }, [token]);

//   return (
//     <div>
//       <h1>My Repairs</h1>
//       {isLoading ? (
//         <p>Loading...</p>
//       ) : (
//         <ul>
//           {repairs.map((repair, index) => (
//             <li key={index}>
//               <p>Local Date: {repair.localDate}</p>
//               <p>Short Description: {repair.shortDescription}</p>
//               <p>Type of Repair: {repair.typeOfRepair}</p>
//               <p>Status of Repair: {repair.statusOfRepair}</p>
//               <p>Cost: {repair.cost}</p>
//               <p>Description Text: {repair.descriptionText}</p>
//               <p>Property ID: {repair.propertyId}</p>
//             </li>
//           ))}
//         </ul>
//       )}
//     </div>
//   );
// };

// export default RepairFetchedData;