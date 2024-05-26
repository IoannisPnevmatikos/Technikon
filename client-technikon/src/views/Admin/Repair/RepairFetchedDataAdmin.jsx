import useToken from '../../../stores/useToken';
import React from 'react';
import { useLocation } from 'react-router-dom';

const RepairFetchedDataAdmin = () => {
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

export default RepairFetchedDataAdmin;