import React, { useCallback } from 'react';
import { fileRegister, fileSelector } from '../../state/reducer/file.reducer';
import { useDispatch, useSelector } from 'react-redux';

import cameraIcon from '../../assets/image/ico_camera.svg';
import AddAPhoto from '@material-ui/icons/AddAPhoto';

const FileUpload = ({ image, setImages }) => {
    const dispatch = useDispatch();

    const fileList = useSelector(fileSelector).fileList;

    const handleSelectedImg = useCallback((e) => {
        e.preventDefault();

        const formData = new FormData();
        const imgFile = e.target.files;
        const imgUrl = URL.createObjectURL(imgFile[0]);

        for (let i = 0; i < imgFile.length; i++) {
            formData.append('uploadFiles', imgFile[i]);
        }

        dispatch(fileRegister(formData));
        setImages(imgUrl);
    });

    return (
        <>
            <div className="avatar-wrapper">
                <label
                    htmlFor="fileUpload"
                    // className="mb-0 font-size-4 text-smoke"
                >
                    {image === null ? (
                        <AddAPhoto className="thumnail-camera-icon" fontSize="large" />
                    ) : (
                        fileList.map((file) => {
                            return (
                                <img
                                    className="pic_basic btn_custom_file_camera thumnail-size"
                                    src={`http://localhost:8080/files/display?fileName=s_${file.uuid}_${file.fileName}`}
                                />
                            );
                        })
                    )}
                </label>
                <input type="file" accept="image/*" id="fileUpload" className="sr-only" onChange={handleSelectedImg} />
            </div>
        </>
    );
};

export default FileUpload;
